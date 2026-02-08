public class AIAgent {
    private String modelVersion;
    private float accuracyScore;

    public AIAgent(String modelVersion) {
        this.modelVersion = modelVersion;
        this.accuracyScore = 0.85f;
    }

    public String generateResponse(String queryText) {
        if (queryText.toLowerCase().contains("error")) {
            return "Please check the system configuration and retry.";
        }
        return "Your query has been processed successfully.";
    }

    public float analyzeConfidence() {
        return accuracyScore;
    }
}