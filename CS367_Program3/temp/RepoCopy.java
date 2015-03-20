import java.util.ArrayList;
import java.util.List;

/**
 * Represents a copy of a version of repository.
 * @author
 *
 */
public class RepoCopy {
	
	/* The name of the repository represented by the copy. */
	private final String repoName;
	
	/* The version of the copy. */
	private final int version;
	
	/* The list of the documents in the copy. */
	private final List<Document> docs;
	
	/**
	 * Constructs a RepoCopy object.
	 * @param reponame The name of the repository.
	 * @param version The version number.
	 * @param docs The list of documents.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public RepoCopy(String repoName, int version, List<Document> docs) {
		
		if (repoName == null || docs == null) {
			throw new IllegalArgumentException();
		}
		this.repoName = repoName;
		this.version = version;
		this.docs = new ArrayList<Document>();
    	
    	for (Document d : docs) {
			this.docs.add(
					new Document(d.getName(),d.getContent(),d.getRepoName()));
		}
	}
	
	/**
	 * Returns the version number of this copy.
	 * @return Version number
	 */
	public int getVersion() {
		return this.version;
	}
	
	/**
	 * Returns a copy of list of documents belonging to the copy.
	 * @return The list of documents.
	 */
	public List<Document> getDocuments() {
		return new ArrayList<Document>(this.docs);
	}
	
	/**
	 * Get the name of the repository represented by the local copy.
	 * @return The repsoitory's name.
	 */
	public String getReponame() {
		return this.repoName;
	}
		
	/**
	 * Adds a new document to the doc list if the document doesn't already 
	 * exists. 
	 * @param doc The document to add.
	 * @return True, if the document got added, false if document already exists.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public boolean addDoc(Document doc) {
		
		if (docs == null) {
			throw new IllegalArgumentException();
		}
    	
		if (this.docs.contains(doc)) {
			return false;
		}
		
		this.docs.add(doc);
		return true;
	}
	
	/**
	 * Deletes a document from the copy.
	 * @param doc the document to delete.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public void delDoc(Document doc) {
		if (docs == null) {
			throw new IllegalArgumentException();
		}
		this.docs.remove(doc);
	}
	
	/**
	 * Returns a particular document from the copy.
	 * @param docName The document's name which has to be retrieved.
	 * @return Doc object if the document is found, null otherwise.
	 * @throws IllegalArgumentException if any argument is null. 
	 */
	public Document getDoc(String docName) {
		
		if (docName == null) {
			throw new IllegalArgumentException();
		}
    	
		for (Document d : this.docs) {
			if (d.getName().equals(docName)) {
				return d;
			}
		}
		return null;
	}
		
	@Override
	public String toString() {
		String str = "=================================== \n";
		str += "Reponame: " + this.repoName + "\t\t"
				+ "Version: " + this.version +"\n"
				+ "-----------Documents--------------- \n";
		int count = 0;
		for (Document d : this.docs) {
			str += ++count + ". " + d.getName() + "\n";
		}
		str += this.docs.size() + " document(s) found.\n"
				+ "===================================";
		return str;
	}
}
