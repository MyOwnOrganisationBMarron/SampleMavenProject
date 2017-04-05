package sampleMavenProject;

import org.junit.Assert;
import org.junit.Test;

import fr.dawan.formation.domain.Formation;

public class FormationTest {

	@Test
	public void getterTest() {
		Formation test = new Formation("Jenkins", 2, 1500);
		Assert.assertEquals("Jenkins", test.getSujet());

	}

}
