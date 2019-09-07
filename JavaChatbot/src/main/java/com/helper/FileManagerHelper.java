package com.helper;
import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * This example still compiles the source from file
 */

public class FileManagerHelper {
	
	final static String filePath = "temp/TestPower.java";
	static String compilerResult = "PASS";
	
	public String CompilerByAPI(String inputContent) throws IOException {
		
		CreateJavaFile(inputContent);
		
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        MyDiagnosticListener listener = new MyDiagnosticListener();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(listener, null, null);

        File file = new File(filePath);
        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, listener, null, null, javaFileObjects);
        
//        if (task.call()) {
//            return compilerResult;
//        }
        
        
        fileManager.close();
        
        return compilerResult;
        
    }

    private static final class MyDiagnosticListener implements DiagnosticListener {
        public void report(Diagnostic diagnostic) {
            //to have more control over formatting etc, use indivual methods of
            //diagnostic instead
        	compilerResult = diagnostic.toString();
        }
    }
    
    public static void CreateJavaFile(String inputContent) throws IOException {
    	
    	String content1 = "public class TestPower {\n" + 
    			"	static int power(int m, int n) {";
    	
    	String content2 = "}\n" + 
    			"}";
    	
//    	String inputContent = "if (n == 0)\n" + 
//    			"			return 1;\n" + 
//    			"		else {\n" + 
//    			"			int y = power(m, n - 1);\n" + 
//    			"			return y * m;\n" + 
//    			"		}";

        // If the file doesn't exists, create and write to it
		// If the file exists, truncate (remove all content) and write to it
//        try (FileWriter writer = new FileWriter(filePath);
//             BufferedWriter bw = new BufferedWriter(writer)) {
//
//            bw.write(content1 + inputContent + content2);
//
//        } catch (IOException e) {
//            System.err.format("IOException: %s%n", e);
//        }
        
        FileWriter writer = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(content1 + inputContent + content2);
        
    }
    
}
