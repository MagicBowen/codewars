/**
 * Created by wangbo on 2017/10/30.
 */

import javax.script.*;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.oracle.avatar.js.Server;
import com.oracle.avatar.js.Loader;
import com.oracle.avatar.js.log.Logging;

public class Main {
    static private ScriptEngineManager manager = new ScriptEngineManager();
    static private ScriptEngine engine = manager.getEngineByName("JavaScript");

    static private void testPrintWithParaButNoReturn() throws ScriptException {
        String script = (String)"function hello(name) {print ('Hello, ' + name);}";
        engine.eval(script);
        Invocable inv = (Invocable) engine;
        try {
            inv.invokeFunction("hello", "Scripting!!");  //This one works.
        } catch (NoSuchMethodException exception) {
            System.out.println("error occurred : " + exception);
        }
    }

    static private void testFunctionWithParaAndReturn() throws ScriptException {
        String script = (String)"function getValue(a,b) { if (a===\"Number\") return 1;else return b;}";
        engine.eval(script);
        Invocable inv = (Invocable) engine;

        try {
            String a = "Number1";
            int b = 5;
            Object c = inv.invokeFunction("getValue", a, b);
            System.out.println(c);
        } catch (NoSuchMethodException exception) {
            System.out.println("error occurred : " + exception);
        }
    }

    static private void testFunctionInNodeJsFile() throws ScriptException, IOException {
        engine.eval(Files.newBufferedReader(Paths.get("/Users/wangbo/codes/java/scripting/js/example.js"), StandardCharsets.UTF_8));
        Invocable inv = (Invocable) engine;
        try {
            Object c = inv.invokeFunction("localAdd", 1, 2);
            System.out.println(c);
        } catch (NoSuchMethodException exception) {
            System.out.println("error occurred : " + exception);
        }
    }

    static private void testNodeJsFile() throws ScriptException {
        StringWriter scriptWriter = new StringWriter();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        ScriptContext scriptContext = engine.getContext();
        scriptContext.setWriter(scriptWriter);
        try{
            Server server = new Server(engine, new Loader.Core(), new Logging(false), "/Users/wangbo/codes/java/scripting/js");
            server.run("/Users/wangbo/codes/java/scripting/js/example.js");
        } catch(Throwable object){
            System.out.println("error occurred : " + object);
        }

        System.out.println(scriptWriter.toString());
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ScriptException, IOException {
//        testFunctionInNodeJsFile();
        testNodeJsFile();
    }
}
