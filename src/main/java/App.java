
import java.util.HashMap;
import java.util.Map;

import models.ChangeMachine;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");


        get("/", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());


        post("/results", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String content = request.queryParams("content");
            Float cash = Float.parseFloat(content);
            ChangeMachine newChangeMachine = new ChangeMachine();
            String result = newChangeMachine.makeChange(cash);
            model.put("result", result);

            return new ModelAndView(model, "results.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
