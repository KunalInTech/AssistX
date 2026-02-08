public class main {
    public static void main(String[] args) {

        // Create session
        session s = new session("S101", 1);
        System.out.println("Session active: " + s.isActive());

        // Create query
        query q= new query(101, 1, "System error while login", "Technical");

        if (q.validateQuery()) {
            System.out.println("Query validated successfully");
        }

        // AI Agent processes query
        AIAgent agent = new AIAgent("v1.0");
        String reply = agent.generateResponse("System error while login");
        float confidence = agent.analyzeConfidence();

        // Create response
        response r = new response(201, 101, reply, confidence);

        System.out.println("AI Response: " + reply);
        System.out.println("Confidence Score: " + confidence);
        System.out.println("Response valid: " + r.validateResponse());

        // End session
        s.terminateSession();
        System.out.println("Session active after termination: " + s.isActive());
    }
}