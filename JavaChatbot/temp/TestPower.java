public class TestPower {
	static int power(int m, int n) {if (n == 0)
			return 1;
		else {
			int y = power(m, n - 1);
			return y * m;
		}
	}
}