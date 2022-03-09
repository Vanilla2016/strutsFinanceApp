package clinic.finance.comparators;

import java.util.Comparator;
import clinic.finance.beans.BankAccount;

public class BankAccountComparator implements Comparator<BankAccount> {
	@Override
	public int compare(BankAccount o1, BankAccount o2) {
		
		int type1 = o1.getAccountType();
		int type2 = o2.getAccountType();
		
		int answer = 0;
		if(type1 > type2) {
			answer = 1;
		} else if (type1 < type2){
			answer = -1;
		}
		return answer;
	}
}
