package org.casey.basic.gui;

import java.util.List;
import java.sql.Connection;

public interface Hero_DAO {
	public void add(Hero h);	
	public void delete(Hero h);
	public void update(Hero h, Hero h1);
	public Hero getHero(int id);
	public Connection getConnection();
	public List<Hero> list();
	public List<Hero> list(int start, int end);
}
