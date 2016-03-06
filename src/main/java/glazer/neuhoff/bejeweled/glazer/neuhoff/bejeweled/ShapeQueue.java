package glazer.neuhoff.bejeweled.glazer.neuhoff.bejeweled;

import java.util.LinkedList;

public class ShapeQueue {

	private LinkedList<ShapeComponent> list;

	public ShapeQueue() {
		this.list = new LinkedList<ShapeComponent>();
	}

	public void reset() {
		this.list.clear();
	}

	public void enqueue(ShapeComponent data) {
		list.add(data);
	}

	public ShapeComponent dequeue() {
		ShapeComponent data = list.removeFirst();
		return data;
	}

	public ShapeComponent peek() {
		return list.getFirst();
	}

}
