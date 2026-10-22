#include <avr/io.h>
#include <util/delay.h>

int main(void){
    // Yello led
    DDRD |= (1 << PD4);
    // Blue led
    DDRD |= (1 << PD6);
    // Green led
    DDRB |= (1 << PB0);
    // Red led
    DDRB |= (1 << PB3);


    PORTB |= (1 << PD0);
    int toggle1 = 0;
    int toggle2 = 0;

    while (1)
    {
        PORTB ^= (1 << PB3);
        PORTB ^= (1 << PB0);

        _delay_ms(250);

        if (toggle2)
        {
            PORTD ^= (1 << PD6);
            if (toggle1){
                PORTB ^= (1 << PB0);                
            }
        }
        if (toggle1)
        {
            PORTD ^= (1 << PD4);
            toggle2 = !toggle2;
        }

        _delay_ms(250);


        toggle1 = !toggle1;
        
    
        // PORTD |= (1 << PD5);
        // _delay_ms(250);
        // PORTD &= ~(1 << PD5);
        // _delay_ms(250);
        
    }
    
}