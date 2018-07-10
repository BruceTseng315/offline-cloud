package com.turingmaker.service.operation.entity;

/**
 * 
 * @author tzj
 *
 */
public class Taskinfo extends Resourceinfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6693590845720355846L;
	
	private Resourceinfo program;
	
	
	public Taskinfo(String name, String url) {
		super(name, url);
	}
	
	public Taskinfo(String name, String url,Resourceinfo program) {
		super(name, url);
		this.program=program;
	}


	public Taskinfo() {}

	public Taskinfo(Resourceinfo program) {
		super();
		this.program = program;
	}

	public Resourceinfo getProgram() {
		return program;
	}

	public void setProgram(Resourceinfo program) {
		this.program = program;
	}

	@Override
	public String toString() {
		return "Taskinfo [program=" + program + "]"+super.toString();
	}
	
	
}
