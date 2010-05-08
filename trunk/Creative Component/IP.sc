/****************************************************************************
*  Title: IP.sc
*  Author: Brandon Tomlinson
*  Date: 5/5/2010
*  Description: IP to evaluate/control a greenhouse environment based on 
****************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <constant.sh>

import "i_receiver";
import "i_sender";

behavior IP(i_receiver tempdatain, i_receiver moistdatain, i_sender heatcontrolout, i_sender sprinklercontrolout, i_sender tempdataout, i_sender moistdataout)
{

	void main(void) {
		int command, h1, h2, tempest, moistest, diff, RH, shouldRH;

		while(1) 
		{
			tempdatain.receive(&h1, sizeof(h1));//dry-bulb temp
			moistdatain.receive(&h2, sizeof(h2));//wet-bulb temp
			tempdataout.send(&h1, sizeof(h1));
			moistdataout.send(&h2, sizeof(h2));

			diff = h1-h2;
			if (diff == 2)
				if (h1 >= 50 & h1 < 59)
					RH = 88;
				elseif (h1 >=60 & h1 < 69)
					RH = 90;
				elseif (h1 >=70 & h1 < 79)
					RH = 90;
				elseif (h1 >=80 & h1 < 89)
					RH = 91;
			elseif (diff == 4)
				if (h1 >= 50 & h1 < 59)
					RH = 77;
				elseif (h1 >=60 & h1 < 69)
					RH = 79;
				elseif (h1 >=70 & h1 < 79)
					RH = 82;
				elseif (h1 >=80 & h1 < 89)
					RH = 83;
			elseif (diff == 6)
				if (h1 >= 50 & h1 < 59)
					RH = 65;
				elseif (h1 >=60 & h1 < 69)
					RH = 70;
				elseif (h1 >=70 & h1 < 79)
					RH = 74;
				elseif (h1 >=80 & h1 < 89)
					RH = 75;
			elseif (diff == 8)
				if (h1 >= 50 & h1 < 59)
					RH = 55;
				elseif (h1 >=60 & h1 < 69)
					RH = 60;
				elseif (h1 >=70 & h1 < 79)
					RH = 65;
				elseif (h1 >=80 & h1 < 89)
					RH = 68;
			elseif (diff == 10)
				if (h1 >= 50 & h1 < 59)
					RH = 45;
				elseif (h1 >=60 & h1 < 69)
					RH = 52;
				elseif (h1 >=70 & h1 < 79)
					RH = 58;
				elseif (h1 >=80 & h1 < 89)
					RH = 61;
			elseif (diff == 12)
				if (h1 >= 50 & h1 < 59)
					RH = 35;
				elseif (h1 >=60 & h1 < 69)
					RH = 43;
				elseif (h1 >=70 & h1 < 79)
					RH = 51;
				elseif (h1 >=80 & h1 < 89)
					RH = 54;
			elseif (diff == 14)
				if (h1 >= 50 & h1 < 59)
					RH = 25;
				elseif (h1 >=60 & h1 < 69)
					RH = 35;
				elseif (h1 >=70 & h1 < 79)
					RH = 43;
				elseif (h1 >=80 & h1 < 89)
					RH = 47;
			elseif (diff == 16)
				if (h1 >= 50 & h1 < 59)
					RH = 15;
				elseif (h1 >=60 & h1 < 69)
					RH = 27;
				elseif (h1 >=70 & h1 < 79)
					RH = 37;
				elseif (h1 >=80 & h1 < 89)
					RH = 41;
			elseif (diff == 18)
				if (h1 >= 50 & h1 < 59)
					RH = 9;
				elseif (h1 >=60 & h1 < 69)
					RH = 20;
				elseif (h1 >=70 & h1 < 79)
					RH = 31;
				elseif (h1 >=80 & h1 < 89)
					RH = 34;
			elseif (diff == 20)
				if (h1 >= 50 & h1 < 59)
					RH = 0;
				elseif (h1 >=60 & h1 < 69)
					RH = 12;
				elseif (h1 >=70 & h1 < 79)
					RH = 24;
				elseif (h1 >=80 & h1 < 89)
					RH = 29;
			else
				RH = 0;

			if (h1 >= 50 & h1 < 59)
				shouldRH = 83;
			elseif (h1 >=60 & h1 < 69)
				shouldRH = 89;
			elseif (h1 >=70 & h1 < 79)
				shouldRH = 92;
			elseif (h1 >=80 & h1 < 89)
				shouldRH = 95;
		
			if (abs(shouldRH - RH) > DEVIATION)
				command = 1;// turn on 
				command1 = 1;//turn on sprinkler
			else
				command = 0;
				command1 = 0;

			heatcontrolout.send(&command, sizeof(command));
			sprinklercontrolout.send(&command1, sizeof(command1));
		}
	}
};

