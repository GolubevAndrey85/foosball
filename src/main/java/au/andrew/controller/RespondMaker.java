package au.andrew.controller;

import au.andrew.dbProc.DataProc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RespondMaker {
    private int score = 0;
    private int looses = 0;
    private int scoreCompetitor = 0;
    private int loosesCompetitor = 0;
    private boolean comPres = false;
    private String query;
    private ResultSet rs;
    private StringBuilder statScore = new StringBuilder();
    private StringBuilder statDates = new StringBuilder();
    private String bufScore, bufDate;
    private DataProc dataProc = new DataProc();


    public String[] returnResponse(String mainUserName) {
        query = "SELECT * FROM matches where team1score != -1 or team2score != -1;";
        rs = dataProc.getData(query);

        try {
            while (rs.next()) {
                for (String st : rs.getString(3).split(";")) {
                    if (mainUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                        if ((rs.getInt(5) == 0) && (rs.getInt(6) == 1)) looses++;
                        score += rs.getInt(5);
                        bufDate = rs.getString(2) + ";";
                        bufScore = score + ";";
                        statScore.append(bufScore);
                        statDates.append(bufDate);
                    }
                }
                for (String st : rs.getString(4).split(";")) {
                    if (mainUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                        if ((rs.getInt(6) == 0) && (rs.getInt(5) == 1)) looses++;
                        score += rs.getInt(6);
                        bufDate = rs.getString(2) + ";";
                        bufScore = score + ";";
                        statScore.append(bufScore);
                        statDates.append(bufDate);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataProc.conClose();
        }

        if (statDates.length() == 0)
            if (mainUserName.length() != 0)
                mainUserName = "Hello, " + mainUserName + "! You are unregistered player! You need to play at lest 1 match.";
            else mainUserName = "Hello! You are unregistered player! You need to play at lest 1 match.";
        else mainUserName = "Hello, " + mainUserName + "!";
        return new String[]{mainUserName.trim(), statScore.toString(), statDates.toString(), String.valueOf(score), String.valueOf(looses),
                "", "", "", "",};
    }

    public String[] returnResponse(String mainUserName, String anotherUserName) {

        query = "SELECT * FROM matches where team1score != -1 or team2score != -1;";
        rs = dataProc.getData(query);

        try {
            while (rs.next()) {
                boolean competitor = false;
                for (String st : rs.getString(3).split(";")) {
                    if (mainUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                        competitor = true;
                        if ((rs.getInt(5) == 0) && (rs.getInt(6) == 1)) looses++;
                        score += rs.getInt(5);
                        bufDate = rs.getString(2) + ";";
                        bufScore = score + ";";
                        statScore.append(bufScore);
                        statDates.append(bufDate);
                        break;
                    }
                }
                if (competitor) {
                    for (String st : rs.getString(4).split(";")) {
                        if (anotherUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                            comPres = true;
                            if ((rs.getInt(6) == 0) && (rs.getInt(5) == 1)) loosesCompetitor++;
                            scoreCompetitor += rs.getInt(6);
                            break;
                        }
                    }
                }
                competitor = false;
                for (String st : rs.getString(4).split(";")) {
                    if (mainUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                        competitor = true;
                        if ((rs.getInt(5) == 1) && (rs.getInt(6) == 0)) looses++;
                        score += rs.getInt(6);
                        bufDate = rs.getString(2) + ";";
                        bufScore = score + ";";
                        statScore.append(bufScore);
                        statDates.append(bufDate);
                        break;
                    }
                }
                if (competitor) {
                    for (String st : rs.getString(3).split(";")) {
                        if (anotherUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                            comPres = true;
                            if ((rs.getInt(5) == 0) && (rs.getInt(6) == 1)) loosesCompetitor++;
                            scoreCompetitor += rs.getInt(5);
                            break;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataProc.conClose();
        }

        if (statDates.length() == 0)
            if (mainUserName.length() != 0)
                mainUserName = "Hello, " + mainUserName + "! You are unregistered player! You need to play at lest 1 match.";
            else mainUserName = "Hello! You are unregistered player! You need to play at lest 1 match.";
        else if (comPres) mainUserName = "Hello, " + mainUserName + "!";
        else mainUserName = "Hello, " + mainUserName + "! There is no " + anotherUserName + " player!";

        return new String[]{mainUserName.trim(), statScore.toString(), statDates.toString(), String.valueOf(score), String.valueOf(looses),
                anotherUserName, String.valueOf(scoreCompetitor), String.valueOf(loosesCompetitor), String.valueOf(comPres),};
    }
}
