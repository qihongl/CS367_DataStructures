
/**
 * Represents a change (EDIT/ADD/DELETE) done to a particular document.
 * @author
 *
 */
public class Change {

	/* The changed document. */
	private Document doc;
	
	/* An enumeration denoting type of change: EDIT/ADD/DELETE. */
	public enum Type { ADD, DEL, EDIT };

	/* The type of change */
	private Type type;
	
	/**
	 * Constructs a change object.
	 * @param doc The changed document.
	 * @param type The change type.
	 * @throws IllegalArgumentException for any null arguments.
	 */
	public Change(Document doc, Type type) {
		
    	if (doc == null || type == null) {
			throw new IllegalArgumentException();
		}
    	
		this.doc = doc;
		this.type = type;
	}
	
	/**
	 * Returns the document contained in the change.
	 * @return The document.
	 */
	public Document getDoc() {
		return this.doc;
	}
	
	/**
	 * Returns the type of change.
	 * @return The change type.
	 */
	public Type getType() {
		return this.type;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "Doc name: " + this.doc.getName() + "\n";
		str += "Change Type: " + this.type;
		return str;
	}
}
