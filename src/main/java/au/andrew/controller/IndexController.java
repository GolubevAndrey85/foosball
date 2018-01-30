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

    private String query = "";
    private DataProc dataProc = new DataProc();
    private boolean matchUpdated = false;



    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/")
    public String index(Model a) {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "username", required = false) String mainUserName,
                              @RequestParam(value = "anotherUsername", required = false) String anotherUserName) {

        ModelAndView model = new ModelAndView();
        RespondMaker respondMaker = new RespondMaker();
        String[] resp;
        if (anotherUserName.trim().length() == 0) {
            resp = respondMaker.returnResponse(mainUserName);

        } else {
            resp = respondMaker.returnResponse(mainUserName, anotherUserName);
        }
        model.addObject("mainUserNameAttr", resp[0]);
        model.addObject("mUstatScoreAttr", resp[1]);
        model.addObject("mUstatSatesAttr", resp[2]);
        model.addObject("mUscoreAttr", resp[3]);
        model.addObject("mUloosesAttr", resp[4]);
        model.addObject("anotherUNameAttr", resp[5]);
        model.addObject("anotherUscoreAttr", resp[6]);
        model.addObject("anotherUloosesAttr", resp[7]);
        model.addObject("anotherUpresenceAttr", resp[8]);
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/newMatch", method = RequestMethod.POST)
    public ModelAndView newMatch(@RequestParam(value = "username", required = false) String text) {

        ModelAndView model = new ModelAndView();
        query = "SELECT * FROM matches ORDER BY id DESC LIMIT 1;";
        ResultSet rs = dataProc.getData(query);

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

        if ((!(team1.equals("") || team1.equals(" "))) && (!(team2.equals("") || team2.equals(" ")))) {
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
