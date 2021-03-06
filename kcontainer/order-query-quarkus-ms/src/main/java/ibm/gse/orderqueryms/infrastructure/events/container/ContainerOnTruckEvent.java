package ibm.gse.orderqueryms.infrastructure.events.container;

import ibm.gse.orderqueryms.domain.model.Container;

public class ContainerOnTruckEvent extends ContainerEvent {
	
    private Container payload;
	
	public ContainerOnTruckEvent(long timestampMillis, String version, Container payload) {
        super(timestampMillis, ContainerEvent.TYPE_CONTAINER_ON_TRUCK, version);
        this.payload = payload;
    }

    public ContainerOnTruckEvent() {
    }

	public Container getPayload() {
		return payload;
	}

	public void setPayload(Container payload) {
		this.payload = payload;
	}

}
