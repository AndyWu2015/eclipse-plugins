package com.vogella.extensionpoint.contribution;

import com.vogella.extensionpoint.definition.IGreeter;

public class GreeterGerman2 implements IGreeter {

	@Override
	public void greet() {
		System.out.println("Moin, moin 222 !");
	}
}
