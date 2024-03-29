/****************************************************************************
*  Title: TempProcessing.sc
*  Author: Brandon Tomlinson
*  Date: 04/25/2010
*  Description: Processing of temperature data for heater control ****************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "constant.sh"

import "i_sender";
import "i_receiver";

behavior tempprocessing(i_receiver tempsettingsin, i_receiver tempdatain, i_sender tempdatacontrol, i_sender tempdataout)
{

	void main(void) 
	{
		int command, h1, tempset;

		tempsettingsin.receive(&tempset, sizeof(tempset));
		
		while(1) 
		{
			tempdatain.receive(&h1, sizeof(h1));
		
			if ((h1 - tempset) >( - DEVIATION ))
				command = 1;
			else
				command = 0;

			tempdatacontrol.send(&command, sizeof(command));
			tempdataout.send(&h1, sizeof(h1));
		}
	}

};
