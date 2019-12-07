package sample;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.junit.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

public class XmlUnitSample {

    @Test
    public void testSame() {
    	assertEqualXml("src/test/xml/source.xml", "src/test/xml/comparison-same.xml");
    }

    @Test
    public void testDiff() {
    	assertEqualXml("src/test/xml/source.xml", "src/test/xml/comparison-diff.xml");
    }

    private void assertEqualXml(String sourcePath, String comparisonPath) {

		try {
			FileInputStream source = new FileInputStream(sourcePath);
	        FileInputStream comparison = new FileInputStream(comparisonPath);

	        final Diff diff = DiffBuilder
	                .compare(source)
	                .withTest(comparison)
	                .build();

	        Iterator<Difference> iter = diff.getDifferences().iterator();
	        int size = 0;
	        while (iter.hasNext()) {
	            System.out.println(iter.next().toString());
	            size++;
	        }

	        assertThat(size, equalTo(0));

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
	}
}
