import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Match> matches = getMatchesData();
        List<Deliveries> deliveries = getDeliveriesData();

        getTotalNumberOfMatchesPlayed(matches);
        System.out.println("**********************");
        getNumberOfMatchesWonPerTeam(matches);
        System.out.println("**********************");
        getExtraRunsConcededPerTeam(matches, deliveries);
        System.out.println("**********************");
        getEconomicalBowler(matches, deliveries);
        System.out.println("**********************");
        getWinnerOfTheMatch(matches);

    }

    private static List<Match> getMatchesData() {

        List<Match> matchData = new ArrayList<Match>();

        String matchCsvFile = "/home/syam/IPL/matches.csv";
        String line = "";
        BufferedReader br;
        int skipHeader = 1;
        try {
            br = new BufferedReader(new FileReader(matchCsvFile));
            while ((line = br.readLine()) != null) {
                if (skipHeader == 1) {
                    skipHeader = 0;
                    continue;
                }

                String[] matchRow = line.split(",");
                Match singleMatchDetails = new Match();

                int matchId = Integer.parseInt(matchRow[0]);
                int season = Integer.parseInt(matchRow[1]);
                int dlApplied = Integer.parseInt(matchRow[9]);
                int winByRuns = Integer.parseInt(matchRow[11]);
                int winByWickets = Integer.parseInt(matchRow[12]);
                String city = matchRow[2];
                String date = matchRow[3];
                String team1 = matchRow[4];
                String team2 = matchRow[5];
                String tossWinner = matchRow[6];
                String tossDecision = matchRow[7];
                String result = matchRow[8];
                String winner = matchRow[10];
                String playerOfMatch = matchRow[13];
                String venue = matchRow[14];

                singleMatchDetails.setMatchId(matchId);
                singleMatchDetails.setSeason(season);
                singleMatchDetails.setCity(city);
                singleMatchDetails.setDate(date);
                singleMatchDetails.setTeam1(team1);
                singleMatchDetails.setTeam2(team2);
                singleMatchDetails.setTossWinner(tossWinner);
                singleMatchDetails.setTossDecision(tossDecision);
                singleMatchDetails.setResult(result);
                singleMatchDetails.setDlApplied(dlApplied);
                singleMatchDetails.setWinner(winner);
                singleMatchDetails.setWinByRuns(winByRuns);
                singleMatchDetails.setWinByWickets(winByWickets);
                singleMatchDetails.setPlayerOfMatch(playerOfMatch);
                singleMatchDetails.setVenue(venue);

                matchData.add(singleMatchDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchData;
    }

    private static List<Deliveries> getDeliveriesData() {
        List<Deliveries> deliveriesDataList = new ArrayList<Deliveries>();

        String DeliveriesCsvFile = "/home/syam/IPL/deliveries.csv";
        BufferedReader br;
        String line = "";
        int skipHeader = 1;
        try {
            br = new BufferedReader(new FileReader(DeliveriesCsvFile));
            while ((line = br.readLine()) != null) {
                if (skipHeader == 1) {
                    skipHeader = 0;
                    continue;
                }
                String[] deliveryRow = line.split(",");
                Deliveries deliveries = new Deliveries();

                int matchId = Integer.parseInt(deliveryRow[0]);
                int innings = Integer.parseInt(deliveryRow[1]);
                int over = Integer.parseInt(deliveryRow[4]);
                int ball = Integer.parseInt(deliveryRow[5]);
                int superOver = Integer.parseInt(deliveryRow[9]);
                int wideRun = Integer.parseInt(deliveryRow[10]);
                int byeRun = Integer.parseInt(deliveryRow[11]);
                int legByeRun = Integer.parseInt(deliveryRow[12]);
                int noBallRun = Integer.parseInt(deliveryRow[13]);
                int penaltyRun = Integer.parseInt(deliveryRow[15]);
                int batsmanRun = Integer.parseInt(deliveryRow[15]);
                int extraRun = Integer.parseInt(deliveryRow[16]);
                int totalRun = Integer.parseInt(deliveryRow[17]);
                String battingTeam = deliveryRow[2];
                String bowlingTeam = deliveryRow[3];
                String batsmanName = deliveryRow[6];
                String nonStriker = deliveryRow[7];
                String bowlerName = deliveryRow[8];

                deliveries.setMatchId(matchId);
                deliveries.setInnings(innings);
                deliveries.setBattingTeam(battingTeam);
                deliveries.setBowlingTeam(bowlingTeam);
                deliveries.setOver(over);
                deliveries.setBall(ball);
                deliveries.setBatsmanName(batsmanName);
                deliveries.setNonStriker(nonStriker);
                deliveries.setBowlerName(bowlerName);
                deliveries.setIsSuperOver(superOver);
                deliveries.setWideRuns(wideRun);
                deliveries.setByeRuns(byeRun);
                deliveries.setLegByeRuns(legByeRun);
                deliveries.setNoBallRuns(noBallRun);
                deliveries.setPenaltyRuns(penaltyRun);
                deliveries.setBatsmanRuns(batsmanRun);
                deliveries.setExtraRuns(extraRun);
                deliveries.setTotalRuns(totalRun);

                if (deliveryRow.length > 18) {
                    String playerDismissedName = deliveryRow[18];
                    deliveries.setPlayerDismissed(playerDismissedName);
                }
                if (deliveryRow.length > 19) {
                    String playerDismissalKind = deliveryRow[19];
                    deliveries.setDismissalKind(playerDismissalKind);
                }
                if (deliveryRow.length > 20) {
                    String fielder = deliveryRow[20];
                    deliveries.setFielder(fielder);
                }

                deliveriesDataList.add(deliveries);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveriesDataList;
    }

    private static void getTotalNumberOfMatchesPlayed(List<Match> matches) {

        ArrayList<Integer> total_matches = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            total_matches.add(matches.get(i).getSeason());
        }

        Set<Integer> single_year = new HashSet<>(total_matches);
        for (Integer year : single_year) {
            int matchesPlayedinYear = Collections.frequency(total_matches, year);
            System.out.println("Total Matches Played in " + year + " : " + matchesPlayedinYear);
        }
    }

    private static void getNumberOfMatchesWonPerTeam(List<Match> matches) {

        ArrayList<String> winnersList = new ArrayList<>();
        for (int i = 1; i < matches.size(); i++) {
            if (matches.get(i).getResult().equals("normal")) {
                winnersList.add(matches.get(i).getWinner());
            }
        }

        Set<String> teamNames = new HashSet<>(winnersList);
        for (String s : teamNames) {
            int wonMatchesCount = Collections.frequency(winnersList, s);
            System.out.println("No.Of Matches Won By " + s + " : " + wonMatchesCount);
        }
    }

    private static void getExtraRunsConcededPerTeam(List<Match> matches, List<Deliveries> deliveries) {

        Map<String, Integer> extraRunsMap = new HashMap<String, Integer>();
        Iterator<Match> matchesIterator = matches.iterator();

        while (matchesIterator.hasNext()) {
            Match match = matchesIterator.next();
            if (match.getSeason() == 2016) {
                Iterator<Deliveries> deliveriesIterator = deliveries.iterator();
                while (deliveriesIterator.hasNext()) {
                    Deliveries deliveriesObject = deliveriesIterator.next();
                    if (deliveriesObject.getMatchId() == match.getMatchId()) {

                        String bowlingTeam = deliveriesObject.getBowlingTeam();
                        int extraRuns = deliveriesObject.getExtraRuns();

                        if (extraRunsMap.containsKey(bowlingTeam)) {
                            int previousExtraRuns = extraRunsMap.get(bowlingTeam);
                            extraRunsMap.put(bowlingTeam, previousExtraRuns + extraRuns);
                        } else
                            extraRunsMap.put(bowlingTeam, extraRuns);
                    }
                }
            }

        }
        extraRunsMap.forEach((team, extraRuns) -> {
            System.out.println("Extra Runs Conceded by " + team + " in 2016" + " : " + extraRuns);
        });
    }

    private static void getEconomicalBowler(List<Match> matches, List<Deliveries> deliveries) {
        Map<String, Integer> runsMap = new HashMap<String, Integer>();
        Map<String, Double> oversMap = new HashMap<String, Double>();
        Map<String, Double> economyMap = new HashMap<String, Double>();

        Iterator<Match> matchesIterator = matches.iterator();
        while (matchesIterator.hasNext()) {
            Match match = matchesIterator.next();
            if (match.getSeason() == 2015) {
                Iterator<Deliveries> deliveriesIterator = deliveries.iterator();
                while (deliveriesIterator.hasNext()) {
                    Deliveries deliveriesObj = deliveriesIterator.next();
                    if (deliveriesObj.getMatchId() == match.getMatchId()) {

                        String bowlerName = deliveriesObj.getBowlerName();
                        int totalRuns = deliveriesObj.getTotalRuns();

                        if (runsMap.containsKey(bowlerName)) {
                            int previousRuns = runsMap.get(bowlerName);
                            runsMap.put(bowlerName, previousRuns + totalRuns);
                        } else {
                            runsMap.put(bowlerName, totalRuns);
                        }

                        if (oversMap.containsKey(bowlerName)) {
                            Double totalBalls = oversMap.get(bowlerName);
                            oversMap.put(bowlerName, totalBalls + 1.0);
                        } else {
                            oversMap.put(bowlerName, 1.0);
                        }
                    }
                }
            }
        }

        // eco= totalRuns/overs
        Set<String> bowlerNamesSet = oversMap.keySet();
        for (String bowler : bowlerNamesSet) {
            double totalBalls = oversMap.get(bowler);
            oversMap.put(bowler, (totalBalls / 6));
        }

        for (String bowler : bowlerNamesSet) {
            double totalRuns = runsMap.get(bowler);
            double totalOvers = oversMap.get(bowler);
            double economyRate = totalRuns / totalOvers;
            economyMap.put(bowler, economyRate);
        }

        System.out.println("Economy Rate of A Bowler in 2015:\n");
        economyMap.forEach((bowlerName, economyRate) -> {
            System.out.println(bowlerName + " : " + economyRate);
        });
    }

    private static void getWinnerOfTheMatch(List<Match> matches) {
        Iterator<Match> matchesIterator = matches.iterator();
        while (matchesIterator.hasNext()) {
            Match match = matchesIterator.next();
            // String
            String wonTeam = match.getWinner();
            String winner = wonTeam + " won the Match by ";
            int wonByRuns = match.getWinByRuns();

            if (wonByRuns == 0) {
                int wonByWickets = match.getWinByWickets();
                winner = winner + wonByWickets + " Wickets";
            } else {
                winner = winner + wonByRuns + " Runs";
            }

            String team1 = match.getTeam1();
            String team2 = match.getTeam2();
            if (wonTeam == team1) {
                winner = winner + " on " + team2;
            } else {
                winner = winner + " on " + team1;
            }
            System.out.println(winner);
        }

    }
}