package ca.nines.ise.writer;

import ca.nines.ise.dom.DOM;
import nu.xom.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.regex.Pattern;


public class XMLWriterSectioningTest extends XMLWriterTestBase {

    // CONTAINERS
    @Test
    @Ignore
    public void quoteIsAContainer() {
        // .. and is lowercase
    }
    @Test
    @Ignore
    public void linegroupIsAContainer() {

    }
    @Test
    @Ignore
    public void margIsAContainer() {

    }
    @Test
    @Ignore
    public void stanzaIsAContainer() {
        // assume stanza -> linegroup
    }
    @Test
    @Ignore
    public void bracegroupIsAContainer() {

    }

    // SPECIAL HANDLING OF STANZA
    @Test
    @Ignore
    public void translateStanzaToLinegroup() {

    }
    @Test
    @Ignore
    public void translateStazaNumberIntoMS() {

    }

    // SECTIONING MILESTONES
    @Test
    @Ignore
    public void frontmatterIsAMilestone() {

    }
    @Test
    @Ignore
    public void backmatterIsAMilestone() {
        
    }
    @Test
    @Ignore
    public void divIsAMilestone() {
        // (//d:div) .size()==1, children==0
    }
    @Test
    @Ignore
    public void actIsAMilestone() {
        
    }
    @Test
    @Ignore
    public void sceneIsAMilestone() {
        
    }
    @Test
    @Ignore
    public void pageIsAMilestone() {
        
    }
    @Test
    @Ignore
    public void modeIsAMilestone() {

    }
    @Test
    @Ignore
    public void colIsAMilestone() {

    }

    // MAIN MILESTONE
    @Test
    @Ignore
    public void createMainMilestoneAfterFrontmatter() {

    }

    // CLOSING MODE MILESTONE
    @Test
    @Ignore
    public void resetModeAfterClosing() {

    }

    // SPECIAL COLUMN HANDLING
    @Test
    @Ignore
    public void renameCol0ToReset() {

    }
    @Test
    @Ignore
    public void createMissingColumns() {
        // <COL n="2"/> --> <col/><col/>
    }

  

}