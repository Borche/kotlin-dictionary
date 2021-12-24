import React from "react";
import { connect } from "react-redux";

import { addSwedishWord } from "../../redux/current-state/current-state.actions";
import { selectCurrentAction } from "../../redux/current-state/current-state.selectors";

import "./new-word-button.styles.scss";

class Header extends React.Component {
  render() {
    const { addSwedishWordStart } = this.props;
    console.log("props", this.props);

    return (
      <button onClick={addSwedishWordStart}>
        Add {this.props.language} Word
      </button>
    );
  }
}

const mapDispatchToProps = (dispatch) => ({
  addSwedishWordStart: () => dispatch(addSwedishWord()),
});

const mapStateToProps = (state) => ({
  currentAction: selectCurrentAction(state),
});

export default connect(mapStateToProps, mapDispatchToProps)(Header);
