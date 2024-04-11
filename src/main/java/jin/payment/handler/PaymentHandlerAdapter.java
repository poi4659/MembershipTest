package jin.payment.handler;

public class PaymentHandlerAdapter {
	private boolean redirect = false;
	
	private String path = null;
	
	public boolean isRedirect() {
		return redirect;
	}
	
	public void setRedirect() {
		this.redirect = redirect;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
