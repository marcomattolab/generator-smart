package it.eng.generate.template.test;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateTestWorkflow extends AbstractTemplate{

	public TemplateTestWorkflow(DataBase database) {
		super(database);
	}

	public String getTypeTemplate() {
		String typeTemplate = Utils.replace(ConfigCreateProject.getIstance().getSrcWorkflowFolder(),".","/");
		return typeTemplate;
	}

	public String getTypeFile() {
		return "java";
	}

	public String getBody() {
		//https://www.buildmystring.com/
		ConfigCreateProject conf = ConfigCreateProject.getIstance();
		String body = 
		"package "+ conf.getPackageclass() + "." + conf.getSrcWorkflowFolder()+";\n\n" +
		"import java.util.HashMap;\n" + 
		"import java.util.Map;\n" + 
		"import junit.framework.TestCase;\n" + 
		"import org.junit.Test;\n" + 
		"import org.springframework.boot.test.context.SpringBootTest;\n" + 
		"import org.springframework.test.context.junit4.SpringRunner;\n"+
		"import org.junit.runner.RunWith;\n" + 
		"import org.junit.runners.JUnit4;\n" + 
		"import "+ conf.getPackageclass() + "." + conf.getSrcWorkflowFolder()+".Workflow;\n\n" + 
		
		//TODO TEST THIS!
		//"@RunWith(JUnit4.class)\n" + 
		"@RunWith(SpringRunner.class)\n" + 
		"@SpringBootTest(classes = "+Utils.getClassNameCamelCase(conf.getProjectName()) + conf.getApp()+".class)\r\n" +
		
		"public class WorkflowTest extends TestCase {\n\n" + 
		"	private enum TestEnum {\n" + 
		"		A,\n" + 
		"		B,\n" + 
		"		C,\n" + 
		"		D,\n" + 
		"	}\n\n" + 
		"	private static class TestWorkflow extends Workflow<TestEnum> {\n\n" + 
		"		/**\n" + 
		"		 * Constructor.\n" + 
		"		 */\n" + 
		"		public TestWorkflow() {\n" + 
		"			super( buildWorklowMap() );\n" + 
		"		}\n" + 
		"		\n" + 
		"		/**\n" + 
		"		 * Builds the workflow steps map.\n" + 
		"		 * @return Steps map.\n" + 
		"		 */\n" + 
		"		private static Map<TestEnum, TestEnum[]> buildWorklowMap() {\n" + 
		"			Map<TestEnum, TestEnum[]> map = new HashMap<>();\n" + 
		"			map.put( TestEnum.A, new TestEnum[]{ TestEnum.B, TestEnum.C });\n" + 
		"			map.put( TestEnum.B, new TestEnum[]{ TestEnum.C });\n" + 
		"			map.put( TestEnum.C, new TestEnum[]{ TestEnum.A, TestEnum.D });\n" + 
		"			map.put( TestEnum.D, null);\n" + 
		"			return map;\n" + 
		"		}\n" + 
		"		\n" + 
		"	}\n\n" + 
		"	/**\n" + 
		"	 * Tests that allowed steps are returned as valid.\n" + 
		"	 */\n" + 
		"	@Test\n" + 
		"	public void testAllowedSteps() {\n" + 
		"		TestWorkflow workflow = new TestWorkflow();\n" + 
		"		assertTrue( workflow.isValidStep( TestEnum.A, TestEnum.B ) );\n" + 
		"		assertTrue( workflow.isValidStep( TestEnum.A, TestEnum.C ) );\n" + 
		"		assertTrue( workflow.isValidStep( TestEnum.B, TestEnum.C ) );\n" + 
		"		assertTrue( workflow.isValidStep( TestEnum.C, TestEnum.A ) );\n" + 
		"		assertTrue( workflow.isValidStep( TestEnum.C, TestEnum.D ) );\n" + 
		"	}\n\n" + 
		"	/**\n" + 
		"	 * Tests that unallowed steps are returna as invalid.\n" + 
		"	 */\n" + 
		"	@Test\n" + 
		"	public void testUnallowedSteps() {\n" + 
		"		TestWorkflow workflow = new TestWorkflow();\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.A, TestEnum.A ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.A, TestEnum.D ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.B, TestEnum.A ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.B, TestEnum.B ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.B, TestEnum.D ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.C, TestEnum.B ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.C, TestEnum.C ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.D, TestEnum.A ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.D, TestEnum.B ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.D, TestEnum.C ) );\n" + 
		"		assertFalse( workflow.isValidStep( TestEnum.D, TestEnum.D ) );\n" + 
		"	}\n\n" + 
		"}\n\n";
		return body;
	}

	public String getClassName() {
		return "WorkflowTest";
	}

	public String getSourceFolder() {
		return "src/test/java";
	}

}
