package com.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.helper.MySQLSelect;
import com.helper.MySQLUpdate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import static com.amazon.ask.request.Predicates.intentName;

public class TestIntent implements RequestHandler {
	
	String speechText = "Start...";
	
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("TestIntent"));
    }

    public Optional<Response> handle(HandlerInput input) {
    	
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        
        Slot testIntentSlot = slots.get("TestResIntent");
        String repromptText = "Please call me again";
        
        if(testIntentSlot != null) {
        	
        	String testValue = testIntentSlot.getValue();
        	
        	speechText = testValue;
        	repromptText = "[repromptText] test content is here!!!";
        	
        }
        
        return input.getResponseBuilder()
	                .withSimpleCard("TestIntentRequest", speechText)
	                .withSpeech(speechText)
	                .withReprompt(repromptText)
	                //.addConfirmIntentDirective(intent)
	                .build();
    }
    
    public String CompilerByAPI(String inputProgram) throws Exception {
	    
		String result = "Start CompilerByAPI...";
		speechText = "Start CompilerByAPI...1";
//		inputProgram = "if (n == 0)\n" + 
//				"			return 1;\n" + 
//				"		else {\n" + 
//				"			int y = power(m, n - 1);\n" + 
//				"			return y * m;\n" + 
//				"		}";
		
		String frontProgram = "public class TestPower {\n" + 
							 "	 static int power(int m, int n) {";
		
		String backProgram = "}\n" + 
				"	public static void main(String[] args) {\n" + 
				"		if (power(2, 3) == 8) {\n" + 
				"			System.out.println(\"Correct\");\n" + 
				"		} else {\n" + 
				"			System.out.println(\"Wrong\");\n" + 
				"		}\n" + 
				"	}\n" + 
				"}";
		
		String program = frontProgram + inputProgram + backProgram;

	    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    
	    speechText = "Start CompilerByAPI...1-1";
	    //Already available DiagnosticListener implementation
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        speechText = "Start CompilerByAPI...1-2: diagnostics= " + diagnostics.toString();
        //that's how we get the instance of StandardJavaFileManager
        //the compiler just returns the new instance.
        //StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);
	    
        //speechText = "Start CompilerByAPI...1-3: standardFileManager= " + standardFileManager.toString();
	    JavaFileObject compilationUnit = new StringJavaFileObject("TestPower", program);
	    speechText = "Start CompilerByAPI...1-4";
	    SimpleJavaFileManager fileManager = new SimpleJavaFileManager(compiler.getStandardFileManager(null, null, null));
	    
	    speechText = "Start CompilerByAPI...1-5";
	    JavaCompiler.CompilationTask compilationTask = compiler.getTask(
	    		null, fileManager, 
	    		diagnostics, null, 
	    		null, Arrays.asList(compilationUnit));
	    
	    speechText = "Start CompilerByAPI...2";
	    
	    Future<Boolean> future = Executors.newFixedThreadPool(1).submit(compilationTask);
        Boolean compilerResult = future.get();
        if (compilerResult != null && compilerResult) {
        	result = "Compilation done";
        	speechText = "Start CompilerByAPI...3";
        } else {
            //diagnostics.getDiagnostics().forEach(System.out::println);
        	result = "Compilation done: Error";
        	speechText = "Start CompilerByAPI...4";
//        	for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
//        		result = String.format("Error on line %d because of %s%n",
//                        diagnostic.getLineNumber(),
//                        diagnostic.getMessage(null));
//            }
        	
        }
        //standardFileManager.close();
        
        return result;
        
	}
    
    private static class StringJavaFileObject extends SimpleJavaFileObject {
	    private final String code;

	    public StringJavaFileObject(String name, String code) {
	      super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
	      this.code = code;
	    }

	    @Override
	    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
	      return code;
	    }
	}
    
    private static class ClassJavaFileObject extends SimpleJavaFileObject {
	    private final ByteArrayOutputStream outputStream;
	    private final String className;

	    protected ClassJavaFileObject(String className, Kind kind) {
	      super(URI.create("mem:///" + className.replace('.', '/') + kind.extension), kind);
	      this.className = className;
	      outputStream = new ByteArrayOutputStream();
	    }

	    @Override
	    public OutputStream openOutputStream() throws IOException {
	      return outputStream;
	    }

	    public byte[] getBytes() {
	      return outputStream.toByteArray();
	    }

	    public String getClassName() {
	      return className;
	    }
    }
    
    private static class SimpleJavaFileManager extends ForwardingJavaFileManager {
	    private final List<ClassJavaFileObject> outputFiles;

	    protected SimpleJavaFileManager(JavaFileManager fileManager) {
	      super(fileManager);
	      outputFiles = new ArrayList<ClassJavaFileObject>();
	    }

	    @Override
	    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
	      ClassJavaFileObject file = new ClassJavaFileObject(className, kind);
	      outputFiles.add(file);
	      return file;
	    }

	    public List<ClassJavaFileObject> getGeneratedOutputFiles() {
	      return outputFiles;
	    }
    }

}
