package tinqin.zoobff.operations;

public interface OperationProcessor<I extends OperationInput, R extends OperationResult> {
    R process(I input);
}
