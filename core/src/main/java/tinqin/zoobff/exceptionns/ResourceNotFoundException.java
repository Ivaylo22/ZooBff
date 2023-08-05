package tinqin.zoobff.exceptionns;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String name, String id) {
        super(String.format("%s with Id %s not found", name, id));
    }
}
