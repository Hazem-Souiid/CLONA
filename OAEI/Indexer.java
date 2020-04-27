package CLONA.OAEI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.codecs.simpletext.SimpleTextCodec;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;



public class Indexer {

	/**
	 * Creates a new instance of Indexer
	 */
	public Indexer() {
	}

	private IndexWriter indexWriter = null;

	public IndexWriter getIndexWriter(boolean create, String directory) throws IOException {
		if (indexWriter == null) {
			Directory indexDir = FSDirectory.open(new File(directory));
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_2, new ClassicAnalyzer());
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
			config.setCodec(new SimpleTextCodec());
			indexWriter = new IndexWriter(indexDir, config);
			indexWriter.deleteAll();
		}
		return indexWriter;
	}

	public void closeIndexWriter() throws IOException {
		if (indexWriter != null) {
			indexWriter.close();
		}
	}

	public void indexConcept(Concept c, String directory) throws IOException {

		IndexWriter writer = getIndexWriter(false, directory);
		Document doc = new Document();
		doc.add(new StringField("id", c.getName(), Field.Store.YES));
		doc.add(new StringField("Content1", c.getLabel(), Field.Store.YES));
		doc.add(new StringField("text", c.getLabelTraduction(), Field.Store.YES));
		doc.add(new TextField("content", PrepareToIndexing.GetReadyForIndexing(c.getLabelTraduction()), Field.Store.NO));
		writer.addDocument(doc);
	}

	public void rebuildIndexes(ArrayList<Concept> list, String directory) throws IOException {

		getIndexWriter(true, directory);

		for (Concept c : list) {
			if (c.getLabelTraduction() != null)
				indexConcept(c, directory);
		}

		closeIndexWriter();
	}
}
