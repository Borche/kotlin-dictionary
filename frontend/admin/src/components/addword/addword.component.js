import React from "react";
import { connect } from "react-redux";

import { addSwedishWord } from "../../redux/current-state/current-state.actions";

import "./addword.styles.scss";

class Header extends React.Component {
  componentDidMount() {
    const { language } = this.props;
    console.log(this.props);
  }

  render() {
    const { addSwedishWordStart } = this.props;

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

export default connect(null, mapDispatchToProps)(Header);
