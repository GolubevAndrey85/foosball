package au.andrew.controller;


import au.andrew.dbProc.DataProc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.*;

@Controller
public class IndexController {

    // JDBC variables for opening and managing connection
    private static ResultSet rs;
    private String query = "";
    private DataProc dataProc = new DataProc();
    private boolean matchUpdated = false;


    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/")
    public String index(Model m) {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "username", required = false) String mainUserName,
                              @RequestParam(value = "anotherUsername", required = false) String anotherUserName) {

        ModelAndView model = new ModelAndView();

        if (anotherUserName.trim().length() == 0) {
            int score = 0;
            int looses = 0;
            StringBuilder statScore = new StringBuilder();
            StringBuilder statDates = new StringBuilder();
            String bufScore, bufDate;
            query = "SELECT * FROM matches where team1score != -1 or team2score != -1;";
            rs = dataProc.getData(query);

            try {
                while (rs.next()) {
                    for (String st : rs.getString(3).split(";")) {
                        if (mainUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                            if ((rs.getInt(5) == 0)&&(rs.getInt(6) == 1)) looses++;
                            score += rs.getInt(5);
                            bufDate = rs.getString(2) + ";";
                            bufScore = score + ";";
                            statScore.append(bufScore);
                            statDates.append(bufDate);
                        }
                    }
                    for (String st : rs.getString(4).split(";")) {
                        if (mainUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                            if ((rs.getInt(6) == 0)&&(rs.getInt(5) == 1)) looses++;
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
                if (mainUserName.length() != 0) mainUserName = "Hello, " + mainUserName + "! You are unregistered player! You need to play at lest 1 match.";
                else mainUserName = "Hello! You are unregistered player! You need to play at lest 1 match.";
            else mainUserName = "Hello, " + mainUserName + "!";

            model.addObject("mainUserNameAttr", mainUserName.trim());
            model.addObject("mUstatScoreAttr", statScore.toString());
            model.addObject("mUstatSatesAttr", statDates.toString());
            model.addObject("mUscoreAttr", String.valueOf(score));
            model.addObject("mUloosesAttr", String.valueOf(looses));
            model.addObject("anotherUNameAttr", "");
            model.addObject("anotherUscoreAttr", "");
            model.addObject("anotherUloosesAttr", "");
            model.addObject("anotherUpresenceAttr", "");

        } else {
            int score = 0;
            int looses = 0;
            int scoreCompetitor = 0;
            int loosesCompetitor = 0;
            boolean competitor = false;
            boolean comPres = false;

            StringBuilder statScore = new StringBuilder();
            StringBuilder statDates = new StringBuilder();
            String bufScore, bufDate;
            query = "SELECT * FROM matches where team1score != -1 or team2score != -1;";
            rs = dataProc.getData(query);

            try {
                while (rs.next()) {
                    competitor = false;
                    for (String st : rs.getString(3).split(";")) {
                        if (mainUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                            competitor = true;
                            if ((rs.getInt(5) == 0)&&(rs.getInt(6) == 1)) looses++;
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
                                if ((rs.getInt(6) == 0)&&(rs.getInt(5) == 1)) loosesCompetitor++;
                                scoreCompetitor += rs.getInt(6);
                                break;
                            }
                        }
                    }
                    competitor = false;
                    for (String st : rs.getString(4).split(";")) {
                        if (mainUserName.toLowerCase().trim().equals(st.toLowerCase().trim())) {
                            competitor = true;
                            if ((rs.getInt(5) == 1)&&(rs.getInt(6) == 0)) looses++;
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
                                if ((rs.getInt(5) == 0)&&(rs.getInt(6) == 1)) loosesCompetitor++;
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
                if (mainUserName.length() != 0) mainUserName = "Hello, " + mainUserName + "! You are unregistered player! You need to play at lest 1 match.";
                else mainUserName = "Hello! You are unregistered player! You need to play at lest 1 match.";
            else if (comPres) mainUserName = "Hello, " + mainUserName + "!";
            else mainUserName = "Hello, " + mainUserName + "! There is no " + anotherUserName + " player!";

            model.addObject("mainUserNameAttr", mainUserName.trim());
            model.addObject("mUstatScoreAttr", statScore.toString());
            model.addObject("mUstatSatesAttr", statDates.toString());
            model.addObject("mUscoreAttr", String.valueOf(score));
            model.addObject("mUloosesAttr", String.valueOf(looses));
            model.addObject("anotherUNameAttr", anotherUserName);
            model.addObject("anotherUscoreAttr",String.valueOf(scoreCompetitor));
            model.addObject("anotherUloosesAttr", String.valueOf(loosesCompetitor));
            model.addObject("anotherUpresenceAttr", comPres);
        }

        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/newMatch", method = RequestMethod.POST)
    public ModelAndView newMatch(@RequestParam(value = "username", required = false) String text) {

        ModelAndView model = new ModelAndView();
        query = "SELECT * FROM matches ORDER BY id DESC LIMIT 1;";
        rs = dataProc.getData(query);

        try {
            while (rs.next()) {

                if (rs.getString(5).equals("-1") || rs.getString(6).equals("-1")) {
                    model.addObject("team1Attribute", rs.getString(3));
                    model.addObject("team2Attribute", rs.getString(4));
                    model.addObject("team1scoreAttribute", " ");
                    model.addObject("team2scoreAttribute", " ");
                    model.addObject("message", "You need to fill the previous match results!");
                    matchUpdated = true;
                } else {
                    model.addObject("team1scoreAttribute", " ");
                    model.addObject("team2scoreAttribute", " ");
                    matchUpdated = false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            dataProc.conClose();
        }
        model.setViewName("newMatch");
        return model;
    }

    @RequestMapping(value = "/saveMatch", method = RequestMethod.POST)
    public ModelAndView saveMatch(@RequestParam(value = "team1", required = true) String team1,
                                  @RequestParam(value = "team2", required = true) String team2,
                                  @RequestParam(value = "date", required = true) String date,
                                  @RequestParam(value = "team1score", required = true) String team1score,
                                  @RequestParam(value = "team2score", required = true) String team2score,
                                  @RequestParam(value = "matchDetails", required = false) String matchDetails) {

        ModelAndView model = new ModelAndView();

        if ((!(team1.equals("")||team1.equals(" ")))&&(!(team2.equals("")||team2.equals(" ")))){
            if (team1score.equals(" ") || team1score.equals("")) team1score = "-1";
            if (team2score.equals(" ") || team2score.equals("")) team2score = "-1";
            if (!team1score.equals("-1") && !team2score.equals("-1") && matchUpdated) {
                query = "delete from matches where team1score = -1 or team2score = -1;";
                dataProc.putData(query);
                matchUpdated = false;
            }

            if (!team1score.equals("-1") || !team2score.equals("-1")) {
                if (Integer.valueOf(team1score.trim()) > Integer.valueOf(team2score.trim())) {
                    team1score = "1";
                    team2score = "0";
                } else if (Integer.valueOf(team1score.trim()) < Integer.valueOf(team2score.trim())) {
                    team1score = "0";
                    team2score = "1";
                } else {
                    team1score = "0";
                    team2score = "0";
                }
            }

            query = "INSERT INTO matches(gameTime, team1, team2, team1score, team2score, details) " +
                    "VALUES ('" + date + "', '" + team1 + "', '" + team2 + "', " + team1score + ", " + team2score + ", '" + matchDetails + "');";
            dataProc.putData(query);
            model.addObject("someAttribute", "matches");
        }
        model.setViewName("index");
        return model;
    }
}
