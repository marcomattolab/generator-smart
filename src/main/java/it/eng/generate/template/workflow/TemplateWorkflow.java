package it.eng.generate.template.workflow;

import it.eng.generate.ConfigCreateProject;
import it.eng.generate.DataBase;
import it.eng.generate.Utils;
import it.eng.generate.template.AbstractTemplate;

public class TemplateWorkflow extends AbstractTemplate{

	public TemplateWorkflow(DataBase database) {
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
		"import java.util.ArrayList;\n" + 
		"import java.util.Collections;\n" + 
		"import java.util.HashMap;\n" + 
		"import java.util.List;\n" + 
		"import java.util.Map;\n" + 
		"import java.util.Map.Entry;\n\n" + 
		
		"public class "+getClassName()+"<T extends Enum<?>> {\n\n" + 
		"	/**\n" + 
		"	 * Map of the next statuses.\n" + 
		"	 * The key is the current status, the value is the available statuses to go to.\n" + 
		"	 */\n" + 
		"	private final Map<T, List<T>> nextStatusMap;\n\n" + 
		"	/**\n" + 
		"	 * Static constructor.\n" + 
		"	 * @param steps \n" + 
		"	 */\n" + 
		"	@SuppressWarnings(\"unchecked\")\n" + 
		"	public "+getClassName()+"( final Map<T, T[]> steps ) {\n" + 
		"		// Initialize variables\n" + 
		"		Map<T, List<T>> statusesMap = new HashMap<>();\n" + 
		"		// Check steps\n" + 
		"		if( steps == null || steps.size() == 0 ) {\n" + 
		"			throw new IllegalArgumentException( \"Steps list cannot be empty\" );\n" + 
		"		}\n" + 
		"		// Prevalorize maps to avoid missing statuses\n" + 
		"		for( T status : (T[]) steps.keySet().iterator().next().getClass().getEnumConstants() ) {\n" + 
		"			statusesMap.put( status, new ArrayList<T>() );\n" + 
		"		}\n" + 
		"		// Add steps\n" + 
		"		for( Entry<T, T[]> step : steps.entrySet() ) {\n" + 
		"			T status = step.getKey();\n" + 
		"			T[] nextStatuses = step.getValue();\n" + 
		"			addToMap( statusesMap, status, nextStatuses );\n" + 
		"		}\n" + 
		"		// Set final constant\n" + 
		"		nextStatusMap = Collections.unmodifiableMap( statusesMap );\n" + 
		"	}\n\n" + 
		"	/**\n" + 
		"	 * Adds a status transition to map.\n" + 
		"	 * @param map Transitions map.\n" + 
		"	 * @param fromStatus Start status.\n" + 
		"	 * @param toStatuses Reachable statuses.\n" + 
		"	 */\n" + 
		"	private void addToMap(\n" + 
		"			Map<T, List<T>> map,\n" + 
		"			T fromStatus,\n" + 
		"			@SuppressWarnings(\"unchecked\") T ... toStatuses) {\n" + 
		"		List<T> statusesList = new ArrayList<>();\n" + 
		"		if( toStatuses != null ) {\n" + 
		"			for( T nextStatus : toStatuses ) {\n" + 
		"				statusesList.add( nextStatus );\n" + 
		"			}\n" + 
		"		}\n" + 
		"		map.put( fromStatus, Collections.unmodifiableList( statusesList ) );\n" + 
		"	}\n\n" + 
		"	/** Returns a list of all available statuses which can be reached from the given status.\n" + 
		"	 * @param fromStatus Start status.\n" + 
		"	 * @return List of available statuses.\n" + 
		"	 */\n" + 
		"	public List<T> getNextAvailableStatuses( T fromStatus ) {\n" + 
		"		return nextStatusMap.get( fromStatus );\n" + 
		"	}\n\n" + 
		"	/**\n" + 
		"	 * Checks if the status step is allowed, and throws an exception if it is not.\n" + 
		"	 * The method will also check if user has enough permissions to set the status.\n" + 
		"	 * @param fromStatus Status to leave.\n" + 
		"	 * @param toStatus Status to reach.\n" + 
		"	 */\n" + 
		"	public void validateStep( T fromStatus, T toStatus ) {\n" + 
		"		if( !nextStatusMap.get( fromStatus ).contains( toStatus ) ) {\n" + 
		"			throw new IllegalStateException(\n" + 
		"					\"Cannot change status from \" + fromStatus + \" to \" + toStatus );\n" + 
		"		}\n" + 
		"	}\n\n" + 
		"	/**\n" + 
		"	 * Same as validateStep, but returns false instead of throwing exceptions if\n" + 
		"	 * step is invalid.\n" + 
		"	 * @param fromStatus Status to leave.\n" + 
		"	 * @param toStatus Status to reach.\n" + 
		"	 * @return True if the step is allowed, false otherwise.\n" + 
		"	 */\n" + 
		"	public boolean isValidStep( T fromStatus, T toStatus ) {\n" + 
		"		try {\n" + 
		"			validateStep( fromStatus, toStatus );\n" + 
		"			return true;\n" + 
		"		} catch( Exception e ) {\n" + 
		"			return false;\n" + 
		"		}\n" + 
		"	}\n\n" + 
		"}\n\n";
		return body;
	}

	public String getClassName() {
		return "Workflow";
	}

	public String getSourceFolder() {
		return "src/main/java";
	}

}
