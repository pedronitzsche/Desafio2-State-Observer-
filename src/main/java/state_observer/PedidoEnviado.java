package state_observer;

public class PedidoEnviado extends PedidoEstado {

    private PedidoEnviado() {
    }

    private static PedidoEnviado instance = new PedidoEnviado();

    public static PedidoEnviado getInstance() {
        return instance;
    }

    @Override
    public String getEstado() {
        return "Enviado";
    }

    @Override
    public boolean entregar(Pedido pedido) {
        pedido.setEstado(PedidoEntregue.getInstance());
        return true;
    }
}