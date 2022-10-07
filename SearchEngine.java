import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> query = new ArrayList<String>();
    ArrayList<String> searchResult = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")){
            String[] parameters = url.getQuery().split("=");
            query.add(parameters[1]);
            return parameters[1] + " added!";
        }

        if (url.getPath().equals("/search")){
            String[] parameters = url.getQuery().split("=");
            for(int i = 0; i < query.size(); i++){
                if(query.get(i).contains(parameters[1])){
                    searchResult.add(parameters[1]);
                }
            }
        }
    }
}

/*
s
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Number: %d", num);
        } else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("count")) {
                    num += Integer.parseInt(parameters[1]);
                    return String.format("Number increased by %s! It's now %d", parameters[1], num);
                }
            }
            return "404 Not Found!";
        }
    }
}

*/

class NumberServer {
    public void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
