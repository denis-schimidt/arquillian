package br.com.elo7.estudo_arquillian.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProducer {

	@Produces
	public Logger newLogger( InjectionPoint injectionPoint ){
		return LoggerFactory.getLogger( injectionPoint.getMember().getDeclaringClass() );
	}
}
