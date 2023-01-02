import React from 'react'
import Summary from '../Summary/Summary'
import Casual from '../Casual/Casual'
import About from '../About/About'
import DefaultComponent from './DefaultComponent'

// Text input component
export default function ComponentSwitcher (props) {

    switch(props.renderedComponent){
    case "Summary":
        return <Summary/>
    case "Casual":
        return <Casual/>
    case "About":
        return <About/>
    default:
        return <DefaultComponent/>
    }
}