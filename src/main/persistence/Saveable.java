package persistence;

import java.io.PrintWriter;

// Source: Similar to Saveable interface in TellerApp (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
// Represents data that can be saved to file
public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
