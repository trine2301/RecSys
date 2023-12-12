import {getRandomOrgNo, tl} from '../support/helper'
import {formatCurrency} from '../../src/services/formatter.service'

const randomOrgNo = getRandomOrgNo()

describe('Create transactions', {testIsolation: false}, () => {

    it('should log in with a fresh session', () => {
        cy.clearFbStorage()
        cy.visit('/')

        const {username, password} = Cypress.env('DEMO_USER')
        cy.quickLogin(username, password)
        cy.quickCreateAndSelectOrg(randomOrgNo, 'AddTransaction Test AS')
    })

    // TODO: Make separate component test for multiple lines, both combined and separate deb-cred
    it('should go to accounting page and create some transactions', () => {
        cy.dataCy('menu-logo').click()
        cy.dataCy('menu-entry-reports').click()
        cy.dataCy('report-link-tranjo').click()

        // TODO: Is it possible for the datepicker to support more formats? This sucks for computers!
        cy.addTransactionEntry('020122', '1920', '3000', 1000, 'trans 1')
        cy.addTransactionEntry('020123', '1920', '3000', 1000, 'trans 2')
    })

    it('should open the transaction and click on 1920 and check balance', () => {
        cy.dataCy('transaction-root').click()
        cy.dataCy('transaction-line-account-link').contains('1920').click()
        cy.dataCy('bookkeeping-outgoing-balance').should('contain', formatCurrency(2000))
    })

    it('should click on 2022-1 transaction line', () => {
        cy.quickSetDateFilter('2022-01-01', '2022-12-31')
        cy.dataCy('transaction-link').contains('2022-1').click()
    })

    it('should open the transaction and click on 3000 and check balance', () => {
        cy.dataCy('transaction-line-account-link').contains('3000').click()
        cy.dataCy('bookkeeping-outgoing-balance').should('contain', formatCurrency(-1000))
    })
})