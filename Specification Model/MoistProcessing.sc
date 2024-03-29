/****************************************************************************
*  Title: MoistProcessing.sc
*  Author: Brandon Tomlinson
*  Date: 04/25/2010
*  Description: Processing of moisture data for heater control ****************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "constant.sh"

import "i_sender";
import "i_receiver";


behavior moistprocessing(i_receiver moistsettingsin, i_receiver moistdatain, i_sender moistdatacontrol, i_sender moistdataout)
{
	void main(void) {
		int command, h1, moistset;

		moistsettingsin.receive(&moistset, sizeof(moistset));
		while(1) {
			moistdatain.receive(&h1, sizeof(h1));
		
			if ((h1 - moistset) >( - DEVIATION ))
				command = 1;
			else
				command = 0;

			moistdatacontrol.send(&command, sizeof(command));
			moistdataout.send(&h1, sizeof(h1));
		}
	}
};

