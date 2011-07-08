package eureka.base.exceptions;

public class InvalidOperatorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Override
    public void printStackTrace() {
        System.err.println("Operação inválida: ");
        super.printStackTrace();
    }
}
