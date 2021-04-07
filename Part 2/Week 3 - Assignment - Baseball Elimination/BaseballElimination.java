import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BaseballElimination {

    private final Map<String, Integer> teams;
    private final String[] teamNames;
    private final int[] wins;
    private final int[] losses;
    private final int[] remaining;
    private final int[][] games;
    private final boolean[] isEliminated;
    private final Set<String>[] certificates;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        var in = new In(filename);
        var teamsCount = in.readInt();

        teams = new HashMap<>();
        teamNames = new String[teamsCount];
        wins = new int[teamsCount];
        losses = new int[teamsCount];
        remaining = new int[teamsCount];
        isEliminated = new boolean[teamsCount];
        games = new int[teamsCount][teamsCount];
        certificates = (HashSet<String>[]) new HashSet[teamsCount];

        int i = 0;
        while (!in.isEmpty()) {
            String team = in.readString();
            teams.put(team, i);
            teamNames[i] = team;
            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remaining[i] = in.readInt();

            certificates[i] = new HashSet<>();

            for (int j = 0; j < teamsCount; j++) {
                games[i][j] = in.readInt();
            }

            i++;
        }

        trivialElimination();
        nonTrivialElimination();
    }

    // number of teams
    public int numberOfTeams() {
        return teams.size();
    }

    // all teams
    public Iterable<String> teams() {
        return teams.keySet();
    }

    // number of wins for given team
    public int wins(String team) {
        validateTeamName(team);

        int teamKey = getTeamIndex(team);
        return wins[teamKey];
    }

    // number of losses for given team
    public int losses(String team) {
        validateTeamName(team);

        int teamKey = getTeamIndex(team);
        return losses[teamKey];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        validateTeamName(team);

        int teamKey = getTeamIndex(team);
        return remaining[teamKey];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        validateTeamName(team1);
        validateTeamName(team2);

        int team1Key = getTeamIndex(team1);
        int team2Key = getTeamIndex(team2);
        return games[team1Key][team2Key];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        validateTeamName(team);

        int teamKey = getTeamIndex(team);
        return isEliminated[teamKey];
    }

    private void trivialElimination() {
        var teamsCount = teams.size();
        for (int i = 0; i < teamsCount; i++) {
            for (int j = 0; j < teamsCount; j++) {
                if (i == j)
                    continue;

                if (wins[i] + remaining[i] < wins[j]) {
                    certificates[i].add(teamNames[j]);
                    isEliminated[i] = true;
                }
            }
        }
    }

    private void nonTrivialElimination() {
        var teamsCount = teams.size();
        final int gamesCount = teamsCount * (teamsCount - 1) / 2;
        final int vertexCount = teamsCount + gamesCount + 2;
        final int s = 0, t = vertexCount - 1;

        for (int teamKey = 0; teamKey < teamsCount; teamKey++) {
            if (isEliminated[teamKey])
                continue;

            FlowNetwork flowNetwork = new FlowNetwork(vertexCount);
            int gameVertex = 1;
            for (int i = 0; i < teamsCount; i++) {
                for (int j = i + 1; j < teamsCount; j++) {

                    flowNetwork.addEdge(new FlowEdge(s, gameVertex, games[i][j]));

                    flowNetwork.addEdge(new FlowEdge(gameVertex, gamesCount + i + 1, Double.POSITIVE_INFINITY));
                    flowNetwork.addEdge(new FlowEdge(gameVertex, gamesCount + j + 1, Double.POSITIVE_INFINITY));

                    gameVertex++;
                }

                int teamVertex = gamesCount + i + 1;
                flowNetwork.addEdge(new FlowEdge(teamVertex, t, Math.max(0, wins[teamKey] + remaining[teamKey] - wins[i])));
            }

            FordFulkerson ff = new FordFulkerson(flowNetwork, s, t);

            for (FlowEdge e : flowNetwork.adj(0)) {
                if (e.flow() != e.capacity()) {
                    isEliminated[teamKey] = true;
                    for (int i = 0; i < teamsCount; i++) {
                        if (i == teamKey) {
                            continue;
                        }

                        if (ff.inCut(gamesCount + i + 1)) {
                            certificates[teamKey].add(teamNames[i]);
                        }
                    }
                    break;
                }
            }
        }
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        validateTeamName(team);

        int teamKey = getTeamIndex(team);
        Set<String> certs = certificates[teamKey];
        return certs.isEmpty() ? null : certs;
    }

    private int getTeamIndex(String team) {
        return teams.get(team);
    }

    private void validateTeamName(String team) {
        if (!teams.containsKey(team)) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination("teams5.txt");
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
