package ru.raiffeisen.JsEngineTest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;

@RestController
public class JsTestController {

    private String scriptText;

    @PostConstruct
    private void init()
    {
        BufferedReader rd = null;

        try
        {
            rd = new BufferedReader(new FileReader(new ClassPathResource("script.js").getFile()));

            String inputLine = null;

            StringBuilder builder = new StringBuilder();

            //Store the contents of the file to the StringBuilder.

            while((inputLine = rd.readLine()) != null) {

                builder.append(inputLine);

            }

            scriptText = builder.toString();

        }
        catch(Exception e)
        {
            System.out.print(e.toString());
        }
    }

    @GetMapping(path = "JsTest")
    public String testJs(@RequestParam String requestObject) throws Exception
    {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        Object result = engine.eval(scriptText);

        return "OK" + scriptText;
    }

}
