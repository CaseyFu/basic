package org.casey.basic.jdbc;

import java.lang.annotation.Target;


public @interface DataBaseUtilites {
	String ip();
	int port();
	String database();
	String encoding();
	String LoginName();
	String Password();
}
