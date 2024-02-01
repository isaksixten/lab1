interface loadable <T extends ObjectWithPosition>{
    public void loadCar(T vehicle); 
    public Car unloadCar(T vehicle);
}
