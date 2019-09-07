package com.helper;
import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompilerJavaProgram {
	
	public String CompilerByAPI(String inputProgram) throws Exception {
	    
		String result = "Start CompilerByAPI...";
		
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
	    
	    
	    //Already available DiagnosticListener implementation
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        //that's how we get the instance of StandardJavaFileManager
        //the compiler just returns the new instance.
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);
	    

	    JavaFileObject compilationUnit = new StringJavaFileObject("TestPower", program);

	    SimpleJavaFileManager fileManager = new SimpleJavaFileManager(compiler.getStandardFileManager(null, null, null));

	    JavaCompiler.CompilationTask compilationTask = compiler.getTask(
	    		null, fileManager, 
	    		diagnostics, null, 
	    		null, Arrays.asList(compilationUnit));
	    
	    
	    Future<Boolean> future = Executors.newFixedThreadPool(1).submit(compilationTask);
        Boolean compilerResult = future.get();
        if (compilerResult != null && compilerResult) {
        	result = "Compilation done";
        } else {
            //diagnostics.getDiagnostics().forEach(System.out::println);
        	result = "Compilation done: Error";
//        	for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
//        		result = String.format("Error on line %d because of %s%n",
//                        diagnostic.getLineNumber(),
//                        diagnostic.getMessage(null));
//            }
        	
        }
        standardFileManager.close();
        
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
