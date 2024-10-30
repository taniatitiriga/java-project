public class Patient extends Person{

    private boolean hasWalkingAid;
    private WalkingAid walkingAid = WalkingAid.None;

    public Patient(int weight, int height) {
        super(IDGenerator.generatePatientID(), weight, height);
    }

    @Override
    public int getPriorityLevel() {
        //prioritize over non-emergency doctors and nurses if disabled
        return hasWalkingAid ? 5 : 2;
    }

    @Override
    public int getWeight() {
        int walkingAidWeight = 0;

        switch (walkingAid) {
            case Wheelchair:
                walkingAidWeight = 30;
                break;
            case Frame:
                walkingAidWeight = 15;
                break;
            case Crutches:
                walkingAidWeight = 2;
                break;
            case None:
                walkingAidWeight = 0;
                break;
        }
        return getWeight() + walkingAidWeight;
    }
}