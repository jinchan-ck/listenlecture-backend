package tk.sweetvvck.dao;

public class FactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(DaoFactory.getInstance().getLectureDao()
				.findAllLecture().get(0).getAddress());
	}

}
