package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.util.LinkedList;

public class ShapeQueue {

	private LinkedList<ShapeLabel> list;

	public ShapeQueue() {
		this.list = new LinkedList<ShapeLabel>();
	}

	public void reset() {
		this.list.clear();
	}

	public void enqueue(ShapeLabel data) {
		list.add(data);
	}

	public ShapeLabel dequeue() {
		ShapeLabel data = list.removeFirst();
		return data;
	}

	public ShapeLabel peek() {
		return list.getFirst();
	}

}
