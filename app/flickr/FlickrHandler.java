package flickr;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import play.libs.WS;
import play.libs.WS.WSRequestHolder;
import play.libs.XPath;


public class FlickrHandler {

    interface Arguments{
	String METHOD = "method";
	String API_KEY = "api_key";
	String QUERY = "text";
	String SORT_ORDER = "sort";
	String NUMBER_OF_RESULTS = "per_page";
	
	String FARM = "farm";
	String SERVER = "server";
	String ID = "id";
	String SECRET = "secret";
    }
    
    interface Value{
	String SEARCH_METHOD = "flickr.photos.search";
	String API_KEY = "3c3db7fb07c4e477e2a42fc0eaf52c37";
	String FLICKR_SECRET = "c67f5705d3c3d9f2";
	String RELEVANCE = "relevance";
	String NUMBER_OF_RESULTS = "1";
    }
    
    String searchResult = "";
    

    public WSRequestHolder buildSearchRequest(String q) {
	WSRequestHolder searchRQ = WS.url("http://ycpi.api.flickr.com/services/rest");
	searchRQ.setQueryParameter(Arguments.METHOD, Value.SEARCH_METHOD);
	searchRQ.setQueryParameter(Arguments.API_KEY, Value.API_KEY);
	searchRQ.setQueryParameter(Arguments.QUERY, q);
	searchRQ.setQueryParameter(Arguments.SORT_ORDER, Value.RELEVANCE);
	searchRQ.setQueryParameter(Arguments.NUMBER_OF_RESULTS, Value.NUMBER_OF_RESULTS);
		
	return searchRQ;
    }


    public String handleResponse(Document xmlRS) {
	    Node photoNode = XPath.selectNodes("//photo", xmlRS).item(0);
	    String imageURL;
	    try {
		imageURL = buildSearchResponse(photoNode);
	    } catch (URISyntaxException e) {
		throw new RuntimeException(e);
	    }
	    return imageURL;
    }
    
    public String buildSearchResponse(Node photoNode)  throws URISyntaxException {
	    String response;
	    NamedNodeMap photoAttribs = photoNode.getAttributes();
	    String farmID = photoAttribs.getNamedItem(Arguments.FARM).getTextContent();
	    String serverID = photoAttribs.getNamedItem(Arguments.SERVER).getTextContent();
	    String id = photoAttribs.getNamedItem(Arguments.ID).getTextContent();
	    String secret = photoAttribs.getNamedItem(Arguments.SECRET).getTextContent();
	
	    response = buildResponseURL(farmID, serverID, id, secret);
	    return response;
	}

	private String buildResponseURL(String farmID, String serverID,  String id, String secret) throws URISyntaxException {
	    String response;
	    String path = "/"  +serverID +"/" +id +"_" +secret +"_m.jpg";
	    String server = "farm" + farmID + ".static.flickr.com";
	    URI result = URIUtils.createURI("http", server, 80, path, null, null);
	
	    response = result.toString();
	    return response;
	}
    
}
