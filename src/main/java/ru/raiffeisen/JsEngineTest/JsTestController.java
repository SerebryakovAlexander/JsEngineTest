package ru.raiffeisen.JsEngineTest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.script.Invocable;
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

            // Store the contents of the file to the StringBuilder.

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

    final private ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    @PostMapping(path = "JsTest")
    public Object testJs(@RequestBody String requestObject) throws Exception
    {
        engine.eval(scriptText);

        Object result = ((Invocable)engine).invokeFunction("ruleMain", requestObject);

        return result;
    }

}
