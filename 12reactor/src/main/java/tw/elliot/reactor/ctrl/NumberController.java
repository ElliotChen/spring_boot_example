package tw.elliot.reactor.ctrl;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import tw.elliot.reactor.flux.SlowCounter;

@RestController
@RequestMapping("/numbers")
public class NumberController {

    @GetMapping(path = "/count/{number}")
    public Flux<Integer> countToNumber(@PathVariable("number") int number) {
        return Flux.range(0, number);
    }

    @GetMapping(path = "/slow_count/{number}")
    public Flux<Integer> slowCountToNumber(
            @PathVariable("number") int number) {
        Flux<Integer> dynamicFlux = Flux.create(sink -> {
            SlowCounter.count(sink, number);
        });
        return dynamicFlux;
    }

    @GetMapping(path = "/stream_count/{number}", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> streamCountToNumber(
            @PathVariable("number") int number) {
        Flux<Integer> dynamicFlux = Flux.create(sink -> {
            SlowCounter.count(sink, number);
        });
        return dynamicFlux;
    }
}
