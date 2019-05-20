package jedis.leaderboard;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Main.initRedis();

		Main.updateScore("2", 30.0);
		
		Main.printLeaderboard();
		System.out.println("//////////////////////////////");
		
		Main.updateScore("3", 40.0);
		
		Main.printLeaderboard();
		System.out.println("//////////////////////////////");
		
		Main.printLeaderboardSingleUser("3");

	}

	public static void initRedis() {
		GlobalLeaderboardRedis globalLeader = new GlobalLeaderboardRedis();

		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setId("1");
		user1.setUsername("John");

		User user2 = new User();
		user2.setId("2");
		user2.setUsername("Paul");

		User user3 = new User();
		user3.setId("3");
		user3.setUsername("George");

		User user4 = new User();
		user4.setId("4");
		user4.setUsername("Ringo");

		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
		globalLeader.jedisInit(userList);
	}

	public static void updateScore(String userId, double score) {
		GlobalLeaderboardRedis globalLeader = new GlobalLeaderboardRedis();
		globalLeader.updateScore(userId, score);
	}

	public static void printLeaderboard() {
		GlobalLeaderboardRedis globalLeader = new GlobalLeaderboardRedis();
		List<Leaderboard> leaderboardList = new ArrayList<Leaderboard>();
		leaderboardList = globalLeader.getGlobalLeaderboardRedis();

		for (Leaderboard leaderboard : leaderboardList) {
			System.out.println("Username: " + leaderboard.getUser().getUsername() + " Score: " + leaderboard.getScore()
					+ " Position: " + leaderboard.getPosition());

		}
	}

	public static void printLeaderboardSingleUser(String userId) {
		GlobalLeaderboardRedis globalLeader = new GlobalLeaderboardRedis();
		Leaderboard leaderboard = new Leaderboard();
		leaderboard = globalLeader.getMyLeaderboardPosition(userId);

		System.out.println("Username: " + leaderboard.getUser().getUsername() + " Score: " + leaderboard.getScore()
				+ " Position: " + leaderboard.getPosition());

	}

}
