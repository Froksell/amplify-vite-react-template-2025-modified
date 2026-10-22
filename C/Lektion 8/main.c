unsigned char DDRB;
unsigned char PORTB;
#include <stdlib.h>
#include <stdio.h>
// For `nanosleep()`:
#define _POSIX_C_SOURCE 199309L
#include <time.h>


void printBits(size_t const size, void const* const ptr)
{
    unsigned char* b = (unsigned char*)ptr;
    unsigned char byte;
    int i, j;

    for (i = size - 1; i >= 0; i--)
    {
        for (j = 7; j >= 0; j--)
        {
            byte = (b[i] >> j) & 1;
            printf("%u", byte);
        }
    }
    printf("\n");
}



void _delay_ms(int ms)
{
    struct timespec ts;
    ts.tv_sec = ms / 1000ul;            // whole seconds
    ts.tv_nsec = (ms % 1000ul) * 1000;  // remainder, in nanoseconds
    nanosleep(&ts, NULL);
}

void main()
{
	// set PINB0 to output in DDRB
	DDRB |= (1 << 4);

	while (1)
	{
		// set PINB0 high
		PORTB |= (1 << 4);
		_delay_ms(5000);
		// set PINB0 low
		PORTB &= (1 << 4);
		_delay_ms(5000);

		printBits(sizeof(PORTB), &PORTB);

	}
}