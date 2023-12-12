//TODO documentation. And a before-each?



describe('Compare transactions', () => {

  it('should open a fresh session', () => {
    cy.visit('http://localhost:5172')
  })
})


describe('Navigate to AccountMatcher', () => {
  it('should navigate to AccountMatcher from sidebar', () => {
    cy.visit('http://localhost:5172')
    cy.get('[data-cy="account-matcher-site"]').click()
    cy.get('[data-cy="diff-engine"]')
  })
})


describe('should change time-period', () => {
  it('should click on period-picker and change period', () => {
    cy.visit('http://localhost:5172/accountMatcher#/accountMatcher')
    cy.get('[data-cy="period-picker"]').click()

    cy.get('[data-cy="calendar-date"]').contains('10').click()
    cy.get('[data-cy="calendar-date"]').contains('17').click()

    //TODO finish test. How to access the data???
    //cy.get('[data-cy="period-picker"]').invoke('val').should('equal', '2023-12-10 - 2023-12-20')
  })
})


describe('Should compare transactions and show result', () => {
  it('Should compare transactions and show result', () => {
    cy.visit('http://localhost:5172/accountMatcher#/accountMatcher')
    cy.get('[data-cy="match-transactions-button"]').click()
    cy.get('[data-cy="compare-results"]')
  })
})

//Not testing the actual value, that should be tested in backend.
describe('Should show total discrepancy', () => {
  it('Should show total discrepancy', () => {
    cy.visit('http://localhost:5172/accountMatcher#/accountMatcher')
    cy.get('[data-cy="match-transactions-button"]').click()
    cy.get('[data-cy="total-discrepancy"]')
  })
})





